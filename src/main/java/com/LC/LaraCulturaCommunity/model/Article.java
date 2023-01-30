package com.LC.LaraCulturaCommunity.model;
// Generated Dec 19, 2022, 2:57:24 AM by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;


import lombok.*;


/**
 * Article generated by hbm2java
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "article", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Article extends BaseEntity{


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	private Integer articleId;

	@NotBlank(message = "3amar m3ak ta dak Title llahy7en 3lk")
	private String title;

	@NotBlank(message = "description in arabic must not be khawya llah7aafdek")
    @Column(name = "description_ar")
	private String descriptionAr;


	@NotBlank(message = "rak nsiti english desription ma3amartihax")
    @Column(name = "description_eng")
	private String descriptionEng;

	
	@Transient
	MultipartFile image;



	byte[] photo;
	

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "article")
	// @JoinColumn(name = "id_ep", referencedColumnName = "idEp",nullable = false)
	private Set<Episode> episodes = new HashSet<Episode>(0);
	

}
