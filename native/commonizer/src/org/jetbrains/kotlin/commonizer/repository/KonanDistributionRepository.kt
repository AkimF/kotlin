/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.commonizer.repository

import org.jetbrains.kotlin.commonizer.KonanDistribution
import org.jetbrains.kotlin.commonizer.NativeLibraryLoader
import org.jetbrains.kotlin.commonizer.konan.NativeLibrary
import org.jetbrains.kotlin.commonizer.platformLibsDir
import org.jetbrains.kotlin.konan.target.KonanTarget

internal class KonanDistributionRepository(
    konanDistribution: KonanDistribution,
    targets: Set<KonanTarget>,
    libraryLoader: NativeLibraryLoader,
) : Repository {
    private val librariesByTarget: Map<KonanTarget, Lazy<Set<NativeLibrary>>> =
        targets.associateWith { target ->
            lazy {
                konanDistribution.platformLibsDir
                    .resolve(target.name)
                    .takeIf { it.isDirectory }
                    ?.listFiles()
                    .orEmpty().toList()
                    .map { libraryLoader(it) }
                    .toSet()
            }
        }

    override fun getLibraries(targets: Set<KonanTarget>): Set<NativeLibrary> {
        val target = targets.singleOrNull() ?: return emptySet()
        return librariesByTarget[target]?.value ?: error("Missing target libraries for $target")
    }
}
