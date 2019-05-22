package com.emily.springpostgres

import java.util.HashSet
import org.springframework.beans.BeanWrapperImpl


fun getNullPropertyNames(source: Any): Array<String> {
    val src = BeanWrapperImpl(source)
    val pds = src.propertyDescriptors

    val emptyNames = HashSet<String>()
    for (pd in pds) {
        val srcValue = src.getPropertyValue(pd.name)
        if (srcValue == null) emptyNames.add(pd.name)
    }
    return emptyNames.toTypedArray()
}