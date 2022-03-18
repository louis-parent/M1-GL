import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class Tp9Test
{
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void contextLoads()
    {
        System.out.println("[PASSWORD] : " + this.encoder.encode("password"));
    }
}
