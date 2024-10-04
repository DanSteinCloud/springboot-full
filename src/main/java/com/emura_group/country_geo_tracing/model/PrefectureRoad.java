package com.emura_group.country_geo_tracing.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prefecture_roads", schema = "location")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrefectureRoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prefecture_id")
    private Prefecture prefecture;
    @ManyToOne
    @JoinColumn(name = "road_id")
    private Road road;
    // getters and setters
}
