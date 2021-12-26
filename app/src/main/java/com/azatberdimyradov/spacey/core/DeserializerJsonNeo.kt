package com.azatberdimyradov.spacey.core

import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NearEarthObjectsDto
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.NeoDto
import com.azatberdimyradov.spacey.feature_near_earth_object.data.remote.dto.setNeo
import com.google.gson.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type

class DeserializerJsonNeo : JsonDeserializer<NearEarthObjectsDto> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): NearEarthObjectsDto {

        val neoDto = NearEarthObjectsDto(emptyList())

        Observable.just(json.toString())
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .map { elements ->
                val parsedJson = JsonParser().parse(elements).asJsonObject
                val keys = parsedJson.keySet()
                val neoDtoList = arrayListOf<NeoDto>()
                for (key in keys) {
                    val neoDtoArrays = parsedJson[key].asJsonArray
                    neoDtoArrays.forEach {
                        val neoDto = Gson().fromJson(it.asJsonObject, NeoDto()::class.java)
                        neoDtoList.add(neoDto)
                    }
                }
                return@map neoDtoList
            }
            .flatMap { jsonObjects ->
                Observable.fromArray(jsonObjects)
            }
            .blockingSubscribe { listOfNeo ->
                neoDto.setNeo(listOfNeo)
            }
        return neoDto
    }
}
