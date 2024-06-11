package com.example.inpark.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Place
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationResponse
import com.example.inpark.data.model.User

@Dao
interface ReservationDao {
    @Query("SELECT r.id,r.'userId', r.date_entree, r.date_sortie, r.heure_entree,r.heure_sortie,r.placeId,pl.'parkingId',p.nom,p.location,p.photo,p.price_per_hour,p.rating FROM 'Reservation' r INNER JOIN 'Place' pl ON r.'placeId' = pl.id INNER JOIN 'Parking' p ON pl.'parkingId' = p.id INNER JOIN 'User' u ON u.id=r.'userId' WHERE u.id=:id;")
    fun getAllByUser(id:String):List<ReservationResponse>

    /*@Query("""
        INSERT INTO Place (id, parkingId, floor, is_reserved, number) 
        VALUES (:id, :parkingId, :floor, :is_reserved, :number)
    """)*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addParkingSlot(parkingSlot: Place /*id: Int, parkingId:Int,floor:String,is_reserved:Boolean,number:Int*/)

    /*@Query("""
        INSERT INTO Parking (id, nom, photo, description, closing_hour, latitude, location, longitude, openning_hour, price_per_hour, rating, status, sat, sun, mon, tue, wed, thu, fri) 
        VALUES (:id, :nom, :photo, :description, :closing_hour,:latitude,:location, :longitude, :openning_hour, :price_per_hour, :rating, :status, :sat, :sun, :mon, :tue, :wed, :thu, :fri);

    """)*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addParking(parking:Parking/*id:Int,nom:String,photo:String,description:String,closing_hour:String,latitude:Float,location:String,longitude:Float,
                   openning_hour:String, price_per_hour:Float,rating:Float,status:Boolean,sat:Boolean,sun:Boolean,mon:Boolean,tue:Boolean,wed:Boolean,
                   thu:Boolean,fri:Boolean*/)
    /*@Query("""
        INSERT INTO "Reservation" (id, userId, placeId, date_entree, date_sortie, heure_entree, heure_sortie) 
        VALUES (:id, :userId, :placeId, :date_entree, :date_sortie,:heure_entree,:heure_sortie);

    """)*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createReservation(reservation:Reservation /*id:Int,
                          userId:Int,
                          placeId:Int,
                          date_entree:String,
                          date_sortie:String,
                          heure_entree:String,
                          heure_sortie:String*/
    )
}