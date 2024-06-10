/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.components

import org.jetbrains.kotlin.analysis.api.components.KaOverrideInfoProvider
import org.jetbrains.kotlin.analysis.api.descriptors.KaFe10Session
import org.jetbrains.kotlin.analysis.api.descriptors.components.base.KaFe10SessionComponent
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.getSymbolDescriptor
import org.jetbrains.kotlin.analysis.api.lifetime.KaLifetimeToken
import org.jetbrains.kotlin.analysis.api.symbols.KaCallableSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KaClassOrObjectSymbol
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility
import org.jetbrains.kotlin.descriptors.DescriptorVisibilityUtils.isVisibleWithAnyReceiver

internal class KaFe10OverrideInfoProvider(
    override val analysisSession: KaFe10Session
) : KaOverrideInfoProvider(), KaFe10SessionComponent {
    override val token: KaLifetimeToken
        get() = analysisSession.token

    override fun isVisible(memberSymbol: KaCallableSymbol, classSymbol: KaClassOrObjectSymbol): Boolean  {
        val memberDescriptor = getSymbolDescriptor(memberSymbol) as? DeclarationDescriptorWithVisibility ?: return false
        val classDescriptor = getSymbolDescriptor(classSymbol) ?: return false
        return isVisibleWithAnyReceiver(memberDescriptor, classDescriptor, analysisSession.analysisContext.languageVersionSettings)
    }
}
