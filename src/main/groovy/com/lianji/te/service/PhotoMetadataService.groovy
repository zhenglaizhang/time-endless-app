package com.lianji.te.service

import com.drew.imaging.ImageMetadataReader
import com.drew.imaging.ImageProcessingException
import com.drew.metadata.Metadata
import com.drew.metadata.MetadataException
import com.drew.metadata.exif.ExifDirectoryBase
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.exif.ExifSubIFDDescriptor
import com.drew.metadata.exif.ExifSubIFDDirectory
import com.lianji.te.model.response.Photo

class PhotoMetadataService {
    Photo getPhoto(String name, String desc, InputStream inputStream) throws ImageProcessingException, IOException, MetadataException {
        Metadata metadata = ImageMetadataReader.readMetadata(inputStream)
//        for (Directory directory : metadata.getDirectories()) {
//            for (Tag tag : directory.getTags()) {
//                System.out.println(directory.getClass().getCanonicalName() + "\t=>\t" + tag)
//            }
//        }

        // obtain the Exif directory
        ExifSubIFDDirectory exifDir = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class)
        // create a descriptor
        ExifSubIFDDescriptor exifDesc = new ExifSubIFDDescriptor(exifDir)

        ExifIFD0Directory exifD0Dir = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class)

        int width = exifD0Dir.getInt(ExifDirectoryBase.TAG_IMAGE_WIDTH)
        int height = exifD0Dir.getInt(ExifDirectoryBase.TAG_IMAGE_HEIGHT)
        String make = exifD0Dir.getString(ExifDirectoryBase.TAG_MAKE)
        String model = exifD0Dir.getString(ExifDirectoryBase.TAG_MODEL)
        String copyright = exifD0Dir.getString(ExifDirectoryBase.TAG_COPYRIGHT)

        String fNumber = exifDesc.getFNumberDescription()
        Date originalDate = exifDir.getDate(ExifDirectoryBase.TAG_DATETIME_ORIGINAL)
        String exposureTime = exifDesc.getExposureTimeDescription()
        String exposureModel = exifDesc.getExposureModeDescription()
        int iso = exifDir.getInt(ExifDirectoryBase.TAG_ISO_EQUIVALENT)
        String apertureValue = exifDesc.getApertureValueDescription()
        String maxApertureValue = exifDesc.getMaxApertureValueDescription()
        String focalLength = exifDesc.getFocalLengthDescription()

        new Photo(name: name,
                description: desc,
                dateTimeOriginal: new java.sql.Date(originalDate.getTime()).toLocalDate(),
                width: width,
                height: height,
                exposureTime: exposureTime,
                fnumber: fNumber,
                model: model,
                make: make,
                copyright: copyright,
                isoSpeedRatings: iso,
                apertureValue: apertureValue,
                maxApertureValue: maxApertureValue,
                focallength: focalLength,
                url: "dummy"
        )
    }
}
