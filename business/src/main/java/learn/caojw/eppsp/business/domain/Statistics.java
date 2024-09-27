package learn.caojw.eppsp.business.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Statistics {
    private Long id;
    private String city;
    private String address;
    private Integer so2;
    private Integer co;
    private Integer spm;
    private Integer aqi;
    private LocalDateTime confirm;
    private Long grid;
    private Long feedback;
    private String remarks;
}
