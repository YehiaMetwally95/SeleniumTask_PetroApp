package pojoClassesForAPIs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@ToString
@Jacksonized
@Builder
public class LoginRequestPojo {

    //variables
    private String username;
    private String password;
}
