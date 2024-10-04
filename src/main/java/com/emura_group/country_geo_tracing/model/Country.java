package com.emura_group.country_geo_tracing.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "country", schema="location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Country implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
  @SequenceGenerator(name="country_generator",sequenceName="Country_Generator", allocationSize=1)
  @SequenceGenerator(name="country_generator", sequenceName="country_generator", allocationSize=100)
  private long id;
  
  @Column(name = "country_code")
  public int countryCode;
  
  @Column(name = "country_acronyme")
  private String countryAcronyme;

  @Column(name = "country_name")
  private String countryName;
  
  @Column(name = "country_flag")
  private String country_flag; 
  
  @Column(name = "country_population")
  private int country_population;
  
  @Column(name = "country_men_population")
  private int country_men_population;
  
  @Column(name = "country_women_population")
  private int country_women_population;
  
  @Column(name = "country_children_population")
  private int country_children_population;
  
  @Column(name = "country_main_crop")
  public String countryMainCrop;
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      })
	  @JoinTable(name = "country_roads",
	        joinColumns = { @JoinColumn(name = "country_id") },
	        inverseJoinColumns = { @JoinColumn(name = "road_id") })
	  private Set<Road> roads = new HashSet<>();

}