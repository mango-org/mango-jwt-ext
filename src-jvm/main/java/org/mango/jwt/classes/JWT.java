package org.mango.jwt.classes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mango.jwt.MangoJwtExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseObject;
import php.runtime.reflection.ClassEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Reflection.Abstract
@Reflection.Name("JWT")
@Reflection.Namespace(MangoJwtExtension.NS)
public class JWT extends BaseObject {
    public JWT(Environment env) {
        super(env);
    }

    protected JWT(ClassEntity entity) {
        super(entity);
    }

    public JWT(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public static String extractSubject(String key, String token) {
        return extractClaim(key, token, Claims::getSubject);
    }

    @Reflection.Signature
    public static Date extractExpiration(String key, String token) {
        return extractClaim(key, token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String key, String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(key, token));
    }

    private static Claims extractAllClaims(String key, String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    @Reflection.Signature
    public static boolean isTokenExpired(String key, String token) {
        return extractExpiration(key, token).before(new Date());
    }

    private static String createToken(String key, Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 168))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    @Reflection.Signature
    public static String generateToken(String key, String subject) {
        return createToken(key, new HashMap<>(), subject);
    }
}
