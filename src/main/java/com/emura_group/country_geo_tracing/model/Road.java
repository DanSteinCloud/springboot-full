package com.emura_group.country_geo_tracing.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "road", schema="location")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Road implements Serializable {


private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name="ID", nullable = false, updatable = false)
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  @Column(name = "road_type")
  private String roadType;
  
  @Column(name = "road_name")
  private String roadName;
  
  @Column(name = "width")
  private int width;
  
  @Column(name = "cover")
  private String cover;
  
  @Column(name = "state")
  private String state;

  @Column(name = "description")
  private String description;
  
 
  
  public Road(String roadType, String roadName, int width, String cover, String state, String description) {
		// TODO Auto-generated constructor stub
	this.roadType = roadType;
	this.roadName = roadName;
	this.width = width;
	this.cover = cover;
	this.state = state;
	this.description = description;
			
	}
  
  public Road(long id, String roadType, String roadName, int width, String cover, String state, String description) {
		// TODO Auto-generated constructor stub
	this.id = id;
	this.roadType = roadType;
	this.roadName = roadName;
	this.width = width;
	this.cover = cover;
	this.state = state;
	this.description = description;
			
	}


@ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
	      mappedBy = "roads")
	  @JsonIgnore
	  private Set<Quartier> quartiers = new HashSet<>();
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
	      mappedBy = "roads")
	  @JsonIgnore
	  private Set<Commune> communes = new HashSet<>();
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
	      mappedBy = "roads")
	  @JsonIgnore
	  private Set<Prefecture> prefectures = new HashSet<>();
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
	      mappedBy = "roads")
	  @JsonIgnore
	  private Set<Region> regions = new HashSet<>();
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
	      mappedBy = "roads")
	  @JsonIgnore
	  private Set<Country> countries = new HashSet<>();
}
