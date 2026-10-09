// Arquivo de build da raiz: so declara os plugins (a versao fica no catalogo libs.versions.toml).
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
