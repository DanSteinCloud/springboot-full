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
@Table(name = "quartier", schema="location")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quartier implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quartier_generator")
  @SequenceGenerator(name="quartier_generator", sequenceName="quartier_generator", allocationSize=100)
  private long id;
  
  @Column(name = "quartier_name")
  private String quartierName;

  @Column(name = "description")
  private String description;

  @Column(name = "quartier_population")
  private String quartier_population;
  
  @Column(name = "quartier_men_population")
  private String quartier_men_population;
  
  @Column(name = "quartier_women_population")
  private String quartier_women_population;
  
  @Column(name = "quartier_children_population")
  private String quartier_children_population;
  
  @Column(name = "quartier_main_crop")
  private String quartierMainCrop;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "commune_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Commune commune;
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      })
	  @JoinTable(name = "quartier_roads",
	        joinColumns = { @JoinColumn(name = "quartier_id") },
	        inverseJoinColumns = { @JoinColumn(name = "road_id") })
	  private Set<Road> roads = new HashSet<>();

  
  public void addRoute(Road road) {
	  this.roads.add(road);
	    road.getQuartiers().add(this);
		
  }
  
  public void removeRoute(Long roadId) {
	  Road road = this.roads.stream().filter(t -> t.getId() == roadId).findFirst().orElse(null);
	    if (road != null) {
	      this.roads.remove(road);
	      road.getQuartiers().remove(this);
	    }
	  } 
}
