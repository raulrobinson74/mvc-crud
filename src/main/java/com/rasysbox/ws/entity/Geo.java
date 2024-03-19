package com.rasysbox.ws.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "geos", schema = "public")
public class Geo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "geo_id")
	private Long id;

	@Column(name = "lat")
	private String lat;

	@Column(name = "lng")
	private String lng;
}
