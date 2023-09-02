package org.penguin_stats.android.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

/*
 * Class Below is of public Entity
 */
@Entity
data class CodeI18N(
    val en: String, val ja: String, val ko: String, val zh: String,
)

data class CodeI18NAndResponseZones(
    @Embedded val zone: ResponseZones,
    @Relation(
        parentColumn = "zoneName_i18n",
        entityColumn = "codeI18NId"
    )
    val codeI18N: CodeI18N,
)
//data class CodeI18NAndResponseStages(
//    @Embedded val stages: ResponseStages,
//    @Relation(
//        parentColumn = "code_i18n",
//        entityColumn = "codeI18NId"
//    )
//    val codeI18N: CodeI18N
//)

@Entity
data class Existence(
    @Embedded(prefix = "cn_") val CN: ExistenceValue,
    @Embedded(prefix = "jp_") val JP: ExistenceValue,
    @Embedded(prefix = "kr_") val KR: ExistenceValue,
    @Embedded(prefix = "us_") val US: ExistenceValue,
) {
    data class ExistenceValue(val exist: Boolean, val openTime: Long, val closeTime: Long)
}

data class ExistenceAndResponseZones(
    @Embedded val zone: ResponseZones,
    @Relation(
        parentColumn = "existence",
        entityColumn = "existenceId"
    )
    val codeI18N: Existence,
)
//data class ExistenceAndResponseStages(
//    @Embedded val stage: ResponseStages,
//    @Relation(
//        parentColumn = "existence",
//        entityColumn = "existenceId"
//    )
//    val codeI18N: Existence
//)


/*
 * The class is to provide default value
 * of [Class CodeI18N] & [Class Existence]
 */
object DefaultI18N {
    @JvmStatic
    fun defaultCodeI18N() =
        CodeI18N("default", "default", "default", "default")

    @JvmStatic
    fun defaultExistence(): Existence {
        val exv = Existence.ExistenceValue(false, 0, 0)
        return Existence(exv, exv, exv, exv)
    }
}