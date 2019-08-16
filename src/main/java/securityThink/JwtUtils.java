package securityThink;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-31
 * @Vesion 1.0
 **/
public class JwtUtils {
    public static String JWT_SECERT = "iOiJIUzI1NiJ9";
    public static String TOKEN_PREFIX = "Bearer";
    public static final int expirationDay = 30;
    public static final int updateDay = 5;
    /**
     * 签发JWT
     * @return  String
     *
     */
    public static String createJWT() throws Exception {
        return createJwt(null);
    }

    public static String createJwt(Integer seconds) {
        Calendar expiration = Calendar.getInstance();
        Date iatDate = expiration.getTime(); // 签发时间
        // 设置过期时间
        if(seconds == null || seconds <=0 ) {
            expiration.add(Calendar.DATE, expirationDay);
        } else {
            expiration.add(Calendar.SECOND, seconds);
        }

        // 声明
        Claims claims = Jwts.claims();
        claims.setExpiration(expiration.getTime()); // 过期时间
        claims.setIssuer("baizhikeji"); // 签发人
        claims.setIssuedAt(iatDate); // 签发日期

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
    }
    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public static Result checkJWT(String jwtStr) {
        Result result = new Result();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            Calendar now = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            end.setTime(claims.getExpiration()); // 过期时间

            if(now.compareTo(end) == 1) { // now > end
                result.setCode(ErrorCode.JWT_ERRCODE_EXPIRE.getCode());
                result.setMessage("身份信息过期");
                return result;
            }

            // 延长token有效时间
            now.add(Calendar.DATE, updateDay); // 当最后5天有操作时，更新token
            if(now.compareTo(end) == 1) {
                result.setCode(ErrorCode.JWT_UPDATE.getCode());
                result.setMessage("身份信息已更新");
                result.setToken(createJWT());
            }
            result.setCode(ErrorCode.JWT_SUCCESS.getCode());
            // result.setClaims(claims);
        } catch (ExpiredJwtException e) {
            result.setCode(ErrorCode.JWT_ERRCODE_EXPIRE.getCode());
            result.setMessage("身份信息过期");
        } catch (SignatureException e) {
            result.setCode(ErrorCode.JWT_ERRCODE_FAIL.getCode());
            result.setMessage("身份验证失败");
        } catch (Exception e) {
            result.setCode(ErrorCode.JWT_ERRCODE_FAIL.getCode());
            result.setMessage("身份验证失败");
        }
        return result;
    }

    private static SecretKey generalKey() {
        // byte[] encodedKey = Base64.decode(SystemConstant.JWT_SECERT);
        byte[] encodedKey = JWT_SECERT.getBytes();
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析JWT字符串
     */
    public static Claims parseJWT(String token) throws Exception {
        return Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();
    }
}
