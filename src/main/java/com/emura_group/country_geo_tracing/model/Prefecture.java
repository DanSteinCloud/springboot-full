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
@Table(name = "prefecture", schema="location")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prefecture implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prefecture_generator")
  @SequenceGenerator(name="prefecture_generator", sequenceName="prefecture_generator", allocationSize=100)
  private long id;
  
  @Column(name = "prefecture_name")
  private String prefectureName;

  @Column(name = "description")
  private String description;

  @Column(name = "prefecture_population")
  private String prefecture_population;
  
  @Column(name = "prefecture_men_population")
  private String prefecture_men_population;
  
  @Column(name = "prefecture_women_population")
  private String prefecture_women_population;
  
  @Column(name = "prefecture_children_population")
  private String prefecture_children_population;
  
  @Column(name = "prefecture_main_crop")
  private String prefectureMainCrop;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Region region;
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      })
	  @JoinTable(name = "prefecture_roads",
	        joinColumns = { @JoinColumn(name = "prefecture_id") },
	        inverseJoinColumns = { @JoinColumn(name = "road_id") })
	  private Set<Road> roads = new HashSet<>();
  
  
}