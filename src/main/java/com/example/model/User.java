package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email")
	@Email(message = "*Введите корректный email")
	@NotEmpty(message = "*Введите email")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "*Пароль должен состоять не менее чем из 5 символов")
	@NotEmpty(message = "*Введите пароль")
	@Transient
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "gender")
	private String gender;

	@Column(name = "passportseries")
	private String passportseries;

	@Column(name = "passportnumber")
	private Integer passportnumber;

	@Column(name = "passportissue")
	private String passportissue;

	@Column(name = "passportdate")
	private String passportdate;

	@Column(name = "passportid")
	private String passportid;

	@Column(name = "birthplace")
	private String birthplace;

	@Column(name = "city")
	private String city;

	@Column(name = "adress")
	private String adress;

	@Column(name = "phonehome")
	private String phonehome;

	@Column(name = "phonemobile")
	private String phonemobile;

	@Column(name = "job")
	private String job;

	@Column(name = "jobposition")
	private String jobposition;

	@Column(name = "residencecity")
	private String residencecity;

	@Column(name = "residenceadress")
	private String residenceadress;

	@Column(name = "status")
	private String status;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "disability")
	private String disability;

	@Column(name = "pensioner")
	private String pensioner;

	@Column(name = "income")
	private Integer income;

	@Column(name = "military")
	private String military;


	@Column(name = "active")
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_role", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassportseries() {
		return passportseries;
	}

	public void setPassportseries(String passportseries) {
		this.passportseries = passportseries;
	}

	public Integer getPassportnumber() {
		return passportnumber;
	}

	public void setPassportnumber(Integer passportnumber) {
		this.passportnumber = passportnumber;
	}

	public String getPassportissue() {
		return passportissue;
	}

	public void setPassportissue(String passportissue) {
		this.passportissue = passportissue;
	}

	public String getPassportdate() {
		return passportdate;
	}

	public void setPassportdate(String passportdate) {
		this.passportdate = passportdate;
	}

	public String getPassportid() {
		return passportid;
	}

	public void setPassportid(String passportid) {
		this.passportid = passportid;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhonehome() {
		return phonehome;
	}

	public void setPhonehome(String phonehome) {
		this.phonehome = phonehome;
	}

	public String getPhonemobile() {
		return phonemobile;
	}

	public void setPhonemobile(String phonemobile) {
		this.phonemobile = phonemobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobposition() {
		return jobposition;
	}

	public void setJobposition(String jobposition) {
		this.jobposition = jobposition;
	}

	public String getResidencecity() {
		return residencecity;
	}

	public void setResidencecity(String residencecity) {
		this.residencecity = residencecity;
	}

	public String getResidenceadress() {
		return residenceadress;
	}

	public void setResidenceadress(String residenceadress) {
		this.residenceadress = residenceadress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getPensioner() {
		return pensioner;
	}

	public void setPensioner(String pensioner) {
		this.pensioner = pensioner;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public String getMilitary() {
		return military;
	}

	public void setMilitary(String military) {
		this.military = military;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}

