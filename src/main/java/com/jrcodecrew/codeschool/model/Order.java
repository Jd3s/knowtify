package com.jrcodecrew.codeschool.model;

import com.jrcodecrew.codeschool.dto.EnrollmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

  private double price;
  private String currency="CAD";
  private String method="Paypal";
  private String intent="sale";
  private String description;
  private EnrollmentDto enrollmentDetails;
}
