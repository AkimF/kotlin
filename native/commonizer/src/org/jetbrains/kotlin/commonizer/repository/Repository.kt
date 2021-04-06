/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.commonizer.repository

import org.jetbrains.kotlin.commonizer.konan.NativeLibrary
import org.jetbrains.kotlin.konan.target.KonanTarget

internal interface Repository {
    fun getLibraries(targets: Set<KonanTarget>): Set<NativeLibrary>
}

internal operator fun Repository.plus(other: Repository): Repository {
    if (this is CompositeRepository) {
        return CompositeRepository(this.repositories + other)
    }
    return CompositeRepository(listOf(this, other))
}

private class CompositeRepository(val repositories: Iterable<Repository>) : Repository {
    override fun getLibraries(targets: Set<KonanTarget>): Set<NativeLibrary> {
        return repositories.map { it.getLibraries(targets) }.flatten().toSet()
    }
}

internal object EmptyRepository : Repository {
    override fun getLibraries(targets: Set<KonanTarget>): Set<NativeLibrary> {
        return emptySet()
    }
}
