package com.vishal.bmitrackingapp.data.repository



import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.vishal.bmitrackingapp.domain.model.BmiRecord
import com.vishal.bmitrackingapp.domain.repository.BmiRepository
import kotlinx.coroutines.tasks.await

class FirebaseBmiRepository : BmiRepository {

    private val auth = FirebaseAuth.getInstance()

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getLatestBmi(): Result<BmiRecord?> {

        return try {

            val userId = auth.currentUser?.uid
                ?: return Result.failure(
                    Exception("User not logged in")
                )

            val snapshot = firestore
                .collection("users")
                .document(userId)
                .collection("bmi_records")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()

            val document = snapshot.documents.firstOrNull()

            if (document == null) {
                Result.success(null)
            } else {
                val record = document.toObject(BmiRecord::class.java)
                Result.success(record)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveBmi(
        record: BmiRecord
    ): Result<Unit> {

        return try {

            val user = auth.currentUser
                ?: return Result.failure(
                    Exception("User not logged in")
                )

            val document = firestore
                .collection("users")
                .document(user.uid)
                .collection("bmi_records")
                .document()

            val recordWithId = record.copy(
                id = document.id,
                userId = user.uid
            )

            document
                .set(recordWithId)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBmiHistory(): Result<List<BmiRecord>> {

        return try {

            val userId = auth.currentUser?.uid
                ?: return Result.failure(
                    Exception("User not logged in")
                )

            val snapshot = firestore
                .collection("users")
                .document(userId)
                .collection("bmi_records")
                .orderBy(
                    "timestamp",
                    Query.Direction.DESCENDING
                )
                .get()
                .await()

            val records = snapshot.documents.mapNotNull { document ->
                document.toObject(BmiRecord::class.java)
            }

            Result.success(records)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}