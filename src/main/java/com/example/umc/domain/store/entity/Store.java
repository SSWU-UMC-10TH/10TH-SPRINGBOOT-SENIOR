package com.example.umc.domain.store.entity;

import com.example.demo.global.entity.BaseEntity;
import com.example.umc.domain.store.enums.PlaceProvider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 500)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private PlaceProvider provider;

    @Column(name = "external_place_id", nullable = false, length = 255)
    private String externalPlaceId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
