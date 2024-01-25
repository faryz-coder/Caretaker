package com.bmh.caretaker.model

import com.bmh.caretaker.R

object ConstantGuideAndTips {
    const val guideAndTipsTitle = "Caretaker Guide and Tips "

    private const val educateYourSelf = "Educate Yourself"
    private val educateYourSelfDescription = listOf(
        "Learn About the Cancer: Understand the specific type of cancer, its treatment options, and potential side effects. This knowledge will help you support the patient and communicate effectively with healthcare professionals. "
    )

    private const val emotionalSupport = "Emotional Support"
    private val emotionalSupportDescription = listOf(
        "Open Communication: Encourage open and honest communication. Be a good listener and provide emotional support. Cancer can be overwhelming, and having someone to talk to can be incredibly valuable. ",
        "Address Mental Health: Both the patient and caregiver may experience emotional challenges. Consider seeking professional counseling or support groups to help cope with the emotional aspects of caregiving. ",
    )

    private const val medicalAppointment = "Accompany to Medical Appointments"
    private val medicalAppointmentDescription = listOf(
        "Be Present: Accompany the patient to medical appointments whenever possible. It helps in understanding the treatment plan, asking questions, and being a supportive presence. "
    )

    private const val practicalMatters = "Help with Practical Matters "
    private val practicalMattersDescription = listOf(
        "Organize Medical Information: Maintain a record of medications, treatment schedules, and important contact information for healthcare providers. ",
        "Assist with Daily Tasks: Help with daily activities such as meal preparation, housekeeping, and transportation. These tasks can become challenging during treatment. "
    )

    private const val manageSideEffects = "Manage Side Effects "
    private val manageSideEffectsDescription = listOf(
        "Understand Treatment Side Effects: Be aware of potential side effects of treatments, such as chemotherapy or radiation, and help manage them. This may include providing comfort measures, assisting with medication, or accompanying the patient to medical appointments. "
    )

    private const val provideComfort = "Provide Comfort "
    private val provideComfortDescription = listOf(
        "Create a Comfortable Environment: Ensure that the living space is comfortable and supportive. This includes having cozy and clean surroundings and making adjustments for any physical limitations. "
    )

    private const val encourageHealthyHabits = "Encourage Healthy Habits "
    private val encourageHealthyHabitsDescription = listOf(
        "Promote Nutrition: Support a healthy diet that complements the patient's treatment plan. Consult with healthcare professionals or nutritionists for guidance. ",
        "Encourage Exercise: If possible, encourage light exercise as approved by the healthcare team. Physical activity can contribute to overall well-being. "
    )

    private const val fosterIndependence = "Foster Independence "
    private val fosterIndependenceDescription = listOf(
        "Respect Independence: Allow the patient to maintain a sense of independence when possible. This can positively impact their self-esteem and overall quality of life. "
    )

    private const val carePlanning = "Be Proactive in Care Planning "
    private val carePlanningDescription = listOf(
       "Collaborate with Healthcare Team: Work closely with the patient's healthcare team to understand the treatment plan and actively participate in care planning discussions.  "
    )

    private const val yourself = "Take Care of Yourself "
    private val yourselfDescription = listOf(
       "Take Care of Yourself ",
        "Respite Care: Consider arranging for respite care to allow yourself time for relaxation and self-care. "
    )

    val listGuideAndTips = listOf(
        GuideItem(
            icon = R.drawable.educate_yourself_icon,
            title = educateYourSelf,
            list = educateYourSelfDescription
        ),
        GuideItem(
            icon = R.drawable.emotional_support_icon,
            title = emotionalSupport,
            list = emotionalSupportDescription
        ),
        GuideItem(
            icon = R.drawable.medical_appointment_icon,
            title = medicalAppointment,
            list = medicalAppointmentDescription
        ),
        GuideItem(
            icon = R.drawable.practical_matters_icon,
            title = practicalMatters,
            list = practicalMattersDescription
        ),
        GuideItem(
            icon = R.drawable.manage_side_effects_icon,
            title = manageSideEffects,
            list = manageSideEffectsDescription
        ),
        GuideItem(
            icon = R.drawable.provide_comforts_icon,
            title = provideComfort,
            list = provideComfortDescription
        ),
        GuideItem(
            icon = R.drawable.encourage_healty_habits_icon,
            title = encourageHealthyHabits,
            list = encourageHealthyHabitsDescription
        ),
        GuideItem(
            icon = R.drawable.foster_independence_icon,
            title = fosterIndependence,
            list = fosterIndependenceDescription
        ),
        GuideItem(
            icon = R.drawable.care_planning_icon,
            title = carePlanning,
            list = carePlanningDescription
        ),
        GuideItem(
            icon = R.drawable.yourself_icon,
            title = yourself,
            list = yourselfDescription
        )
    )
}