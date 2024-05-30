package com.example.intercromo.dao

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.intercromo.model.Usuario
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.navigation.ventanasRegistro.VentanasLogIn
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsuarioRepository(navController: NavController) {
    private val auth: FirebaseAuth = Firebase.auth
    private val navegar = navController
    private val firestore = FirebaseFirestore.getInstance()

    private val db = FirebaseFirestore.getInstance()
    private val usuarios = db.collection("usuarios")
    private val intercambiosCollection = db.collection("intercambios")

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun signInEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("InterCromo", "Logueado con éxito")
                navegar.navigate(Rutas.BARRANAVEGACION)
            } else {
                Log.d("InterCromo", "Error al loguearse")
            }
        }
    }

    fun registerEmailPassword(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val nombreEmail = it.result.user?.email?.split("@")?.get(0)
                createUser(name, nombreEmail.toString())
                updateDisplayName(name)
                Log.d("InterCromo", "Registro realizado con éxito")
                navegar.navigate(VentanasLogIn.BienvenidosScreen.ruta)
            } else {
                Log.d("InterCromo", "Error al registrarse")
            }
        }
    }

    private fun createUser(name: String?, email: String?) {
        val userId = auth.currentUser?.uid
        val name = name
        val email = email

        val user = Usuario(name, email, userId.toString(), null, null, 0.0, null, null, null)


        val usuarioRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId!!)

        usuarioRef.set(user.toMap()) // Establecer los datos del usuario en el documento con el userId como ID

            .addOnSuccessListener {
                Log.d("InterCromo", "Creado")
            }.addOnFailureListener {
                Log.d("InterCromo", "Error")
            }


    }

    fun updateDisplayName(name: String) {
        if (auth.currentUser != null) {
            val profileDisplayName = UserProfileChangeRequest.Builder().setDisplayName(name).build()

            auth.currentUser!!.updateProfile(profileDisplayName)
        }
    }

    fun getNombreUsuario(): String? {

        return auth.currentUser?.displayName

    }

    fun getUserProfileImageUrl(): String? {
        return currentUser?.photoUrl?.toString()
    }

    fun signInWithGoogleCredential(credential: AuthCredential) {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        if (currentUser != null) {
                            val userId = currentUser.uid
                            val firestore = FirebaseFirestore.getInstance()
                            val userDocRef = firestore.collection("usuarios").document(userId)

                            userDocRef.get()
                                .addOnSuccessListener { document ->
                                    if (document.exists()) {
                                        Log.d("InterCromo", "Usuario ya existe en Firestore.")
                                    } else {
                                        Log.d(
                                            "InterCromo",
                                            "Usuario no existe en Firestore, creando usuario."
                                        )
                                        createUser(
                                            currentUser.displayName,
                                            currentUser.email.toString()
                                        )
                                    }
                                    // Navegar a la siguiente pantalla después de verificar/crear el usuario
                                    navegar.navigate(Rutas.BARRANAVEGACION)
                                }
                                .addOnFailureListener { e ->
                                    Log.d(
                                        "InterCromo",
                                        "Error obteniendo documento del usuario: $e"
                                    )
                                }
                        }
                    } else {
                        Log.d("InterCromo", "Fallo al registrarse!")
                    }
                }
                .addOnFailureListener {
                    Log.d("InterCromo", "Fallo al registrarse!")
                }
        } catch (ex: Exception) {
            Log.d("InterCromo", "Excepción al loguear con Google: ${ex.localizedMessage}")
        }
    }

    fun llevarAlMenu() {
        if (currentUser != null) {
            navegar.navigate(Rutas.BARRANAVEGACION)
        }
    }

    fun cambiarContraseña(newPassword: String) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.updatePassword(newPassword)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // La contraseña se cambió exitosamente
                    Log.e("InterCromo", "Contraseña cambiada exitosamente.")
                } else {
                    // Ocurrió un error al cambiar la contraseña
                    Log.e("Error", "Error al cambiar la contraseña")
                }
            }
    }

    fun cambiarNombre(newNombre: String) {
        val user = FirebaseAuth.getInstance().currentUser

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(newNombre)
            .build()

        user?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // El nombre se cambió exitosamente
                    Log.e("InterCromo", "Nombre modificado exitosamente.")
                } else {
                    // Ocurrió un error al cambiar el nombre
                    Log.e("Error", "Error al cambiar la contraseña")
                }
            }
    }

    suspend fun actualizarNombreDeUsuario(nuevoNombre: String) {
        val currentUser = auth.currentUser
        currentUser?.let {
            // Actualizar el nombre en Firebase Authentication
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(nuevoNombre)
                .build()
            it.updateProfile(profileUpdates).await()
            // Actualizar el nombre en Firestore
            val userRef = usuarios.document(currentUser.uid)
            userRef.update("name", nuevoNombre).await()
        }
    }

    // Método para actualizar la contraseña en Firebase Authentication
    suspend fun actualizarPassword(nuevaPassword: String) {
        val currentUser = auth.currentUser
        currentUser?.updatePassword(nuevaPassword)?.await()
    }

    fun cerrarSesion(context: Context) {
        try {
            // Cerrar sesión en Firebase
            auth.signOut()

            // Desvincular la cuenta de Google utilizando la API de Google Sign-In
            val googleSignInClient =
                GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
            googleSignInClient.revokeAccess().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Desvinculación exitosa
                    Log.d("InterCromo", "Google account unlinked")
                } else {
                    // Manejar error
                    Log.e("InterCromo", "Failed to unlink Google account")
                }

                // Navegar a la pantalla de inicio de sesión
                navegar.navigate(VentanasLogIn.BienvenidosScreen.ruta)
            }
        } catch (ex: Exception) {
            Log.d("InterCromo", "Excepcion al cerrar sesion " + "${ex.localizedMessage}")
        }
    }

    fun updateFavoritos(cromoId: String) {
        val user = currentUser
        if (user != null) {
            val userId = user.uid
            val userDocRef = firestore.collection("usuarios").document(userId)

            userDocRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    val favoritos =
                        document.get("cromosFavoritos") as? MutableList<String> ?: mutableListOf()
                    if (favoritos.contains(cromoId)) {
                        favoritos.remove(cromoId)
                    } else {
                        favoritos.add(cromoId)
                    }
                    userDocRef.update("cromosFavoritos", favoritos)
                        .addOnSuccessListener {
                            // Actualización exitosa

                        }
                        .addOnFailureListener { e ->
                            // Error al actualizar

                        }
                }
            }.addOnFailureListener { e ->
                // Error al obtener el documento

            }
        }

    }

    fun anadirIntercambio(intercambioId: String, usuarioId: String) {
        val user = usuarioId
        if (user != null) {
            val userDocRef = firestore.collection("usuarios").document(user)


            userDocRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    val intercambiosUsuario =
                        document.get("intercambiosUsuario") as? MutableList<String>
                            ?: mutableListOf()
                    intercambiosUsuario.add(intercambioId)
                    userDocRef.update("intercambiosUsuario", intercambiosUsuario)
                        .addOnSuccessListener {
                            // Actualización exitosa

                        }
                        .addOnFailureListener { e ->
                            // Error al actualizar

                        }
                }
            }.addOnFailureListener { e ->
                // Error al obtener el documento

            }
        }

    }

    fun isFavorite(cromoId: String?): Boolean {
        val user = currentUser
        var favorito: Boolean = false
        if (user != null) {
            val userId = user.uid
            val userDocRef = firestore.collection("usuarios").document(userId)

            userDocRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    val favoritos =
                        document.get("cromosFavoritos") as? MutableList<String> ?: mutableListOf()
                    if (favoritos.contains(cromoId)) {
                        favorito = true
                    } else {
                        favorito = false
                    }


                }
            }
        }

        return favorito
    }


}