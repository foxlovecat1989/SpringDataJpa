package com.edmoresby.springdatajpa.studentidcard;

import com.edmoresby.springdatajpa.student.Student;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = @UniqueConstraint(
                name = "student_id_card_number_unique",
                columnNames = "cardNumber"
        )
)
public class StudentIdCard {

    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    @Id
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "card_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String cardNumber;

    @OneToOne(
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_card_student_fk")
    )
    private Student student;

    public StudentIdCard() {
    }

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student.getId() +
                '}';
    }
}
