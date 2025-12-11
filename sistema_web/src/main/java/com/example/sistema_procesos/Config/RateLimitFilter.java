package com.example.sistema_procesos.Config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitFilter implements Filter {

    private static final int MAX_REQUESTS_PER_SECOND = 5;     // 5 req/s
    private static final int MAX_REQUESTS_PER_MINUTE = 200;   // 200 req/min
    private static final int MAX_REQUESTS_PER_HOUR = 5000;    // 5000 req/h

    private static final long ONE_SECOND = 1000L;
    private static final long ONE_MINUTE = 60 * 1000L;
    private static final long ONE_HOUR = 60 * 60 * 1000L;

    private final Map<String, ClientInfo> clients = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String ip = request.getRemoteAddr();
        String token = request.getHeader("Authorization");

        // Usar token si existe, si no usar IP
        String key = (token != null && token.startsWith("Bearer "))
                ? token.substring(7)
                : ip;

        long now = Instant.now().toEpochMilli();

        ClientInfo info = clients.computeIfAbsent(key, k -> new ClientInfo(now));

        synchronized (info) {

            // Reset ventana de 1s
            if (now - info.secondWindow >= ONE_SECOND) {
                info.secondWindow = now;
                info.secondCount.set(0);
            }
            // Reset ventana de 1min
            if (now - info.minuteWindow >= ONE_MINUTE) {
                info.minuteWindow = now;
                info.minuteCount.set(0);
            }
            // Reset ventana de 1h
            if (now - info.hourWindow >= ONE_HOUR) {
                info.hourWindow = now;
                info.hourCount.set(0);
            }

            int secondCount = info.secondCount.incrementAndGet();
            int minuteCount = info.minuteCount.incrementAndGet();
            int hourCount = info.hourCount.incrementAndGet();

            response.setHeader("X-Rate-Limit-Second", String.valueOf(MAX_REQUESTS_PER_SECOND));
            response.setHeader("X-Rate-Limit-Remaining-Second", String.valueOf(Math.max(0, MAX_REQUESTS_PER_SECOND - secondCount)));

            response.setHeader("X-Rate-Limit-Minute", String.valueOf(MAX_REQUESTS_PER_MINUTE));
            response.setHeader("X-Rate-Limit-Remaining-Minute", String.valueOf(Math.max(0, MAX_REQUESTS_PER_MINUTE - minuteCount)));

            response.setHeader("X-Rate-Limit-Hour", String.valueOf(MAX_REQUESTS_PER_HOUR));
            response.setHeader("X-Rate-Limit-Remaining-Hour", String.valueOf(Math.max(0, MAX_REQUESTS_PER_HOUR - hourCount)));

            if (secondCount > MAX_REQUESTS_PER_SECOND
                    || minuteCount > MAX_REQUESTS_PER_MINUTE
                    || hourCount > MAX_REQUESTS_PER_HOUR) {

                response.setStatus(429);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Demasiadas solicitudes, intenta más tarde\"}");
                return;
            }
        }

        chain.doFilter(req, res);
    }

    private static class ClientInfo {
        long secondWindow;
        long minuteWindow;
        long hourWindow;

        AtomicInteger secondCount = new AtomicInteger(0);
        AtomicInteger minuteCount = new AtomicInteger(0);
        AtomicInteger hourCount = new AtomicInteger(0);

        ClientInfo(long now) {
            this.secondWindow = now;
            this.minuteWindow = now;
            this.hourWindow = now;
        }
    }
}
