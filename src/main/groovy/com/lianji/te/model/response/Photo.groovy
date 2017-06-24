package com.lianji.te.model.response

import com.lianji.te.model.Links
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

import javax.persistence.Column
import javax.validation.constraints.Size
import java.time.LocalDate

//@Entity
//@Table(name = "photos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Photo implements Serializable {
    static final long serialVersionUID = 1L
    @Size(min = 5)
    @Column(name = "name", nullable = false)
    String name
    @Column(name = "description")
    String description
    @Column(name = "date_time_original")
    LocalDate dateTimeOriginal
    @Column(name = "width")
    Integer width
    @Column(name = "height")
    Integer height
    @Column(name = "exposure_time")
    String exposureTime
    @Column(name = "f_number")
    String fnumber
    @Column(name = "model")
    String model
    @Column(name = "make")
    String make
    @Column(name = "copyright")
    String copyright
    @Column(name = "iso_speed_ratings")
    Integer isoSpeedRatings
    @Column(name = "aperture_value")
    String apertureValue
    @Column(name = "max_aperture_value")
    String maxApertureValue
    @Column(name = "focal_length")
    String focallength
    @Column(name = "url")
    String url

    Links link
}
