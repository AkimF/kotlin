/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.codegen.context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jet.codegen.OwnerKind;
import org.jetbrains.jet.codegen.binding.MutableClosure;
import org.jetbrains.jet.lang.descriptors.ClassDescriptor;
import org.jetbrains.jet.lang.descriptors.DeclarationDescriptor;
import org.jetbrains.jet.lang.descriptors.PropertyDescriptor;
import org.jetbrains.jet.lang.resolve.java.JvmAbi;

import java.util.HashMap;
import java.util.Map;

public abstract class FieldOwnerContext<T extends DeclarationDescriptor> extends CodegenContext<T> {
    //default property name -> map<property descriptor -> bytecode name>
    private final Map<String, Map<PropertyDescriptor, String>> fieldNames = new HashMap<String, Map<PropertyDescriptor, String>>();

    public FieldOwnerContext(
            @NotNull T contextDescriptor,
            @NotNull OwnerKind contextKind,
            @Nullable CodegenContext parentContext,
            @Nullable MutableClosure closure,
            @Nullable ClassDescriptor thisDescriptor,
            @Nullable LocalLookup expressionCodegen
    ) {
        super(contextDescriptor, contextKind, parentContext, closure, thisDescriptor, expressionCodegen);
    }

    @NotNull
    public String getFieldName(@NotNull PropertyDescriptor descriptor, boolean isDelegated) {
        assert descriptor.getKind().isReal() : "Only declared properties can have backing fields: " + descriptor;
        boolean isExtension = descriptor.getReceiverParameter() != null;

        return getFieldName(descriptor.getOriginal(), isDelegated, isExtension);
    }

    @NotNull
    private String getFieldName(@NotNull PropertyDescriptor descriptor, boolean isDelegated, boolean isExtension) {
        String defaultPropertyName = JvmAbi.getDefaultPropertyName(descriptor.getName(), isDelegated, isExtension);

        Map<PropertyDescriptor, String> descriptor2Name = fieldNames.get(defaultPropertyName);
        if (descriptor2Name == null) {
            descriptor2Name = new HashMap<PropertyDescriptor, String>();
            fieldNames.put(defaultPropertyName, descriptor2Name);
        }

        String actualName = descriptor2Name.get(descriptor);
        if (actualName != null) return actualName;

        String newName = descriptor2Name.isEmpty() ? defaultPropertyName : defaultPropertyName + "$" + descriptor2Name.size();
        descriptor2Name.put(descriptor, newName);
        return newName;
    }
}
