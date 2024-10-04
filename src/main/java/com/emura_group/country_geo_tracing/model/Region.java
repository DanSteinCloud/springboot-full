package com.emura_group.country_geo_tracing.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "region", schema="location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Region implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_generator")
  @SequenceGenerator(name="region_generator", sequenceName="region_generator", allocationSize=100)
  private long id;
  
  @Column(name = "region_name")
  public String regionName;
  
  @Column(name = "description")
  private String description;

  @Column(name = "region_population")
  private int region_population;
  
  @Column(name = "region_men_population")
  private int region_men_population;
  
  @Column(name = "region_women_population")
  private int region_women_population;
  
  @Column(name = "region_children_population")
  private int region_children_population;
  
  @Column(name = "region_main_crop")
  public String regionMainCrop;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "country_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Country country;
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      })
	  @JoinTable(name = "region_roads",
	        joinColumns = { @JoinColumn(name = "region_id") },
	        inverseJoinColumns = { @JoinColumn(name = "road_id") })
	  private Set<Road> roads = new HashSet<>();

}
