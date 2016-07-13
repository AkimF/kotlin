package org.kotlinnative.translator.llvm

import org.kotlinnative.translator.llvm.types.LLVMType


class LLVMClassVariable(label: String, type: LLVMType, val offset: Int = 0) : LLVMVariable(label, type)
