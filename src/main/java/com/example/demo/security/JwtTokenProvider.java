http
  .csrf().disable()
  .authorizeHttpRequests()
  .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
  .requestMatchers("/api/**").authenticated()
  .and()
  .exceptionHandling()
  .authenticationEntryPoint(jwtAuthenticationEntryPoint);
