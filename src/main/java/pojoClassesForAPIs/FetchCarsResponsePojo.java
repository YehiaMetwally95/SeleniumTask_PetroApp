package pojoClassesForAPIs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FetchCarsResponsePojo {

    //variables
    private String status;
    private int user_id;
    private String message;
    private List<Cars> cars;

    @Getter
    @Setter
    @ToString
    public static class Cars {
        private String car_id;
        private String make;
        private String model;
        private String year;
    }
}
