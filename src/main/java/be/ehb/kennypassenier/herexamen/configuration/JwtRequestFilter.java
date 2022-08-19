package be.ehb.kennypassenier.herexamen.configuration;

import be.ehb.kennypassenier.herexamen.services.JwtService;
import be.ehb.kennypassenier.herexamen.utilities.JwtUtility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // De header van de request bekijken
        String header = request.getHeader("Authorization");
        String jwtToken = null;
        String userName = null;
        // Kijken of de header bestaat en de Bearer token bevat
        if(header != null && header.startsWith("Bearer ")){
            // Token uit de header halen
            jwtToken = header.substring(7);
            try{
                userName = jwtUtility.getUserNameFromToken(jwtToken);
            }
            catch(IllegalArgumentException e){
                System.out.println("Unable to get JWT token");
            }
            catch(ExpiredJwtException e){
                System.out.println("JWT token is expired");
            }
        }
        else{
            System.out.println("JWT token does not start with Bearer");
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtService.loadUserByUsername(userName);
            if(jwtUtility.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
