package id.co.aygo.androidjetpack.view.employes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeModel(val id:String, val name:String, val email:String, val notelp:String, val imageUrl: Int,
                        val jabatan:String,val since:String, val birthDate:String,
                        val company:Company, val atasan:String, val bahawan:List<String>) : Parcelable

@Parcelize
data class Company(val id:String, val companyName:String) : Parcelable