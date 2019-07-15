/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.konan

import com.jetbrains.konan.KonanIconProvider
import icons.CLionIcons
import javax.swing.Icon

class CLionNativeIconProvider : KonanIconProvider {
    override fun getExecutableIcon(): Icon = CLionIcons.CMakeTarget_Executable
}