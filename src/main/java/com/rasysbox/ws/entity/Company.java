package com.rasysbox.ws.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "companies", schema = "public")
public class Company{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "catch_phrase")
	private String catchPhrase;

	@Column(name = "bs")
	private String bs;
}
