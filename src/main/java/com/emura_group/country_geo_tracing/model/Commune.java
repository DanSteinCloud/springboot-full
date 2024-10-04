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
@Table(name = "commune", schema="location")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commune implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commune_generator")
  @SequenceGenerator(name="commune_generator", sequenceName="commune_generator", allocationSize=100)
  private long id;
  
  @Column(name = "commune_name")
  private String communeName;

  @Column(name = "description")
  private String description;

  @Column(name = "commune_population")
  private String commune_population;
  
  private Boolean isCity;
  private Boolean isAgglomeration;
  
  @Column(name = "commune_men_population")
  private String commune_men_population;
  
  @Column(name = "commune_women_population")
  private String commune_women_population;
  
  @Column(name = "commune_children_population")
  private String commune_children_population;
  
  @Column(name = "commune_main_crop")
  private String communeMainCrop;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "prefecture_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Prefecture prefecture;
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      })
	  @JoinTable(name = "commune_roads",
	        joinColumns = { @JoinColumn(name = "commune_id") },
	        inverseJoinColumns = { @JoinColumn(name = "road_id") })
	  private Set<Road> roads = new HashSet<>();
}