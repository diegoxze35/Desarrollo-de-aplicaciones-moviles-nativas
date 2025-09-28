package com.damm.artspace.ui.canvas.theme

import androidx.compose.ui.graphics.Color

// === Paleta de Colores Naranja ===

// --- Tema Claro ---

// Primarios (Naranja Vibrante)
val PrimaryLight = Color(0xFFFB8C00) // Naranja principal (ej. Material Orange 700)
val OnPrimaryLight = Color(0xFFFFFFFF) // Texto blanco sobre naranja
val PrimaryContainerLight = Color(0xFFFFDCC2) // Naranja pálido para contenedores
val OnPrimaryContainerLight = Color(0xFF381D00) // Texto oscuro sobre contenedor naranja pálido

// Secundarios (Marrón Cálido o Naranja Desaturado)
val SecondaryLight = Color(0xFF795548) // Marrón como secundario (ej. Material Brown 500)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFFDEBE0) // Marrón muy pálido
val OnSecondaryContainerLight = Color(0xFF2D160C)

// Terciarios (Ámbar o Amarillo Cálido)
val TertiaryLight = Color(0xFFFFC107) // Ámbar como terciario (ej. Material Amber 500)
val OnTertiaryLight = Color(0xFF000000) // Texto negro sobre ámbar
val TertiaryContainerLight = Color(0xFFFFECB3) // Ámbar pálido
val OnTertiaryContainerLight = Color(0xFF261A00)

// Errores (Rojos estándar)
val ErrorLight = Color(0xFFBA1A1A)
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF410002)

// Fondos y Superficies
val BackgroundLight = Color(0xFFFFF8F4) // Fondo muy claro con un toque cálido
val OnBackgroundLight = Color(0xFF211A13)
val SurfaceLight = Color(0xFFFFF8F4)
val OnSurfaceLight = Color(0xFF211A13)
val SurfaceVariantLight = Color(0xFFF0E0D2) // Variante de superficie ligeramente más oscura/cálida
val OnSurfaceVariantLight = Color(0xFF50443A)

// Outlines
val OutlineLight = Color(0xFF837468)
val OutlineVariantLight = Color(0xFFD5C3B5)

// Otros
val ScrimLight = Color(0xFF000000)
val SurfaceTintLight = PrimaryLight
val InverseSurfaceLight = Color(0xFF362F27)
val InverseOnSurfaceLight = Color(0xFFFBEEE4)
val InversePrimaryLight = Color(0xFFFFB86F) // El PrimaryDark para el tema inverso claro

// --- Tema Oscuro ---

// Primarios (Naranja más Suave para Oscuro)
val PrimaryDark = Color(0xFFFFB86F) // Naranja más claro para tema oscuro (ej. Material Orange 300)
val OnPrimaryDark = Color(0xFF5B2E00) // Texto oscuro sobre naranja claro
val PrimaryContainerDark = Color(0xFF7C4100) // Naranja oscuro para contenedores
val OnPrimaryContainerDark = Color(0xFFFFDCC2) // Texto claro sobre contenedor naranja oscuro

// Secundarios (Marrón Claro)
val SecondaryDark = Color(0xFFE4B7A0) // Marrón claro para tema oscuro
val OnSecondaryDark = Color(0xFF452C1F)
val SecondaryContainerDark = Color(0xFF5D4037) // Marrón medio
val OnSecondaryContainerDark = Color(0xFFFDEBE0)

// Terciarios (Ámbar más Suave para Oscuro)
val TertiaryDark = Color(0xFFFFD54F) // Ámbar más claro para tema oscuro (ej. Material Amber 200)
val OnTertiaryDark = Color(0xFF3E2700)
val TertiaryContainerDark = Color(0xFF5A4400) // Ámbar oscuro
val OnTertiaryContainerDark = Color(0xFFFFECB3)

// Errores (Rojos estándar para oscuro)
val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

// Fondos y Superficies
val BackgroundDark = Color(0xFF211A13) // Fondo oscuro con matiz cálido
val OnBackgroundDark = Color(0xFFEDE0D4)
val SurfaceDark = Color(0xFF211A13)
val OnSurfaceDark = Color(0xFFEDE0D4)
val SurfaceVariantDark = Color(0xFF50443A) // Variante de superficie ligeramente más clara/cálida
val OnSurfaceVariantDark = Color(0xFFD5C3B5)

// Outlines
val OutlineDark = Color(0xFFA08D80)
val OutlineVariantDark = Color(0xFF50443A)

// Otros
val ScrimDark = Color(0xFF000000)
val SurfaceTintDark = PrimaryDark
val InverseSurfaceDark = Color(0xFFEDE0D4)
val InverseOnSurfaceDark = Color(0xFF362F27)
val InversePrimaryDark = Color(0xFFFB8C00) // El PrimaryLight para el tema inverso oscuro