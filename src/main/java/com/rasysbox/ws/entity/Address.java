package com.rasysbox.ws.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "addresses", schema = "public")
public class Address{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;

	@Column(name = "street")
	private String street;

	@Column(name = "suite")
	private String suite;

	@Column(name = "city")
	private String city;

	@Column(name = "zipcode")
	private String zipcode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_id")
	private Geo geo;
}
