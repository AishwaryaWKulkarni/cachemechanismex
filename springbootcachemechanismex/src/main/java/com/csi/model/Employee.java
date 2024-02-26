package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int empId;

    @Pattern(regexp = "[A-Za-z]*", message = "Emp name should not contain space or special characters")
    private String empFirstName;

    @Size(min = 2, message = "Last Name should be atleast 2 chearacters")
    private String empLastName;

    @NotNull
    private String empAddress;

    @Column(unique = true)
    @Range(min = 1000000000, max = 9999999999L, message = "Contact No must contain 10 digit")
    private long empContactNo;

    private double empSalary;

    @Column(unique = true)
    @Range(min = 100000000000L, max = 999999999999L, message = "Emp UID must be 12 digits")
    private long empUID;

    @Column(unique = true)
    private String empPancard;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @Column(unique = true)
    @Email(message = "Email Id must be valid")
    private String empEmailId;

    @Size(min = 4, message = "Password must contain 4 characters")
    private String empPassword;
}
