package com.packpack.pokedexdroid.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.packpack.pokedexdroid.R

enum class TypeEnum(
    val bgTint: Int?,
    val textColor: Int = R.color.white_type_text,
    val bgDrawable: Int = R.drawable.typecolor_blank
) {
    BUG(R.color.bug),
    FAIRY(R.color.fairy, R.color.black_type_text),
    FIRE(R.color.fire),
    GHOST(R.color.ghost),
    NORMAL(R.color.normal, R.color.black_type_text),
    PSYCHIC(R.color.psychic),
    STEEL(R.color.steel, R.color.black_type_text),
    DARK(R.color.dark),
    ELECTRIC(R.color.electric, R.color.black_type_text),
    FIGHTING(R.color.fighting),
    GRASS(R.color.grass, R.color.black_type_text),
    ICE(R.color.ice, R.color.black_type_text),
    POISON(R.color.poison),
    ROCK(R.color.rock),
    WATER(R.color.water),
    DRAGON(null, bgDrawable = R.drawable.typecolor_dragon),
    GROUND(null, R.color.black_type_text, R.drawable.typecolor_ground),
    FLYING(null, R.color.black_type_text, R.drawable.typecolor_flying)
}

class TypeUtils(private val context: Context, typeName: String) {
    private val typeNameCaps = typeName.uppercase()

    fun getBgDrawable(): Drawable? =
        ContextCompat.getDrawable(context, TypeEnum.valueOf(typeNameCaps).bgDrawable)

    fun getBgTint(): Int? = TypeEnum.valueOf(typeNameCaps).bgTint?.let {
        ContextCompat.getColor(context, it)
    }

    fun getTextColor(): Int =
        ContextCompat.getColor(context, TypeEnum.valueOf(typeNameCaps).textColor)
}