package com.bmh.caretaker.model

import com.bmh.caretaker.R

object ConstantDietGuide {
    const val dietGuideDescription = "A well-balanced and nutritious diet is important for cancer patients, as it can support overall health, provide energy, and help manage side effects of treatments. It's crucial to note that individual dietary needs may vary, and it's always best to consult with a healthcare professional or a registered dietitian to create a personalized plan. "

    private const val fruitsAndVegetable = "1. Fruits and Vegetables: "
    private val fruitsAndVegetableDescription = listOf(
        "Colorful Variety: Include a wide range of colorful fruits and vegetables to provide essential vitamins, minerals, and antioxidants. Examples include berries, leafy greens, carrots, and broccoli. "
    )

    private const val leanProteins = "2. Lean Proteins: "
    private val leanProteinsDescription = listOf(
        "Poultry: Skinless poultry is a good source of lean protein. ",
        "Fish: Fatty fish like salmon and mackerel provide omega-3 fatty acids. ",
        "Eggs: Eggs are a versatile and protein-rich option. "
    )

    private const val wholeGrains = "3. Whole Grains: "
    private val wholeGrainsDescription = listOf(
        "Brown Rice: A whole grain that provides fiber and energy. ",
        "Quinoa: A complete protein source and rich in nutrients. ",
        "Oats: High in fiber and can be soothing for the digestive system. "
    )

    private const val healthyFats = "4. Healthy Fats: "
    private val healthyFatsDescription = listOf(
        "Avocado: A source of healthy monounsaturated fats. ",
        "Nuts and Seeds: Almonds, walnuts, flaxseeds, and chia seeds provide healthy fats and other nutrients. ",
        "Olive Oil: Use extra virgin olive oil for cooking or dressing. "
    )

    private const val diaryAlternatives = "5. Dairy or Dairy Alternatives: "
    private val diaryAlternativesDescription = listOf(
        "Greek Yogurt: High in protein and probiotics. ",
        "Cheese: In moderation, for calcium and protein. "
    )

    private const val hydration = "6. Hydration: "
    private val hydrationDescription = listOf(
        "Water: Staying hydrated is crucial, especially during treatment. Herbal teas and broths are also good options. "
    )

    private const val ginger = "7. Ginger: "
    private val gingerDescription = listOf(
        "Ginger: Known for its anti-nausea properties, ginger can be consumed in various forms, such as in tea or added to dishes. "
    )

    private const val vegetarianOption = "8. Protein-Rich Vegetarian Options: "
    private val vegetarianOptionDescription = listOf(
        "Tofu: A plant-based protein source. ",
        "Legumes: Beans and lentils are rich in protein and fiber "
    )

    private const val nutrientDenseFood = "9. High-Calorie Nutrient-Dense Foods: "
    private val nutrientDenseFoodDescription = listOf(
        "Nut Butters: Peanut butter, almond butter, or other nut butters can provide calories and protein. ",
        "Dried Fruits: Calorie-dense and rich in vitamins. "
    )

    private const val minimizeProcessedFood = "10. Whole Foods, Minimize Processed Foods: "
    private val minimizeProcessedFoodDescription = listOf(
        "Whole Foods: Focus on whole, minimally processed foods to maximize nutrient intake. ",
        "Limit Processed Foods: Minimize intake of processed and packaged foods that may contain additives "
    )

    private const val herbalTea = "11. Herbal Teas: "
    private val herbalTeaDescription = listOf(
        "Peppermint or Chamomile Tea: These can be soothing and may help with digestion."
    )

    private const val nutritionPlan = "12. Individualized Nutrition Plan: "
    private val nutritionPlanDescription = listOf(
        "Consult with Professionals: Work closely with a registered dietitian or healthcare team to create an individualized nutrition plan based on specific needs and treatment effects. "
    )

    private const val foodSafety = "13. Be Mindful of Food Safety: "
    private val foodSafetyDescription = listOf(
        "Safe Food Handling: Practice safe food handling to reduce the risk of foodborne illnesses, especially considering potential immunosuppression. "
    )

    private const val frequentMeals = "14. Small, Frequent Meals: "
    private val frequentMealsDescription = listOf(
        "Manage Digestive Issues: Eating smaller, more frequent meals throughout the day may help manage nausea and other digestive issues. "
    )

    val listDietGuide = listOf(
        GuideItem(
            icon = R.drawable.fruits_and_vegetable_icon,
            title = fruitsAndVegetable,
            list = fruitsAndVegetableDescription
        ),
        GuideItem(
            icon = R.drawable.lean_proteins_icon,
            title = leanProteins,
            list = leanProteinsDescription
        ),
        GuideItem(
            icon = R.drawable.whole_grains_icon,
            title = wholeGrains,
            list = wholeGrainsDescription
        ),
        GuideItem(
            icon = R.drawable.healthy_fats_icon,
            title = healthyFats,
            list = healthyFatsDescription
        ),
        GuideItem(
            icon = R.drawable.diary_alternatives_icon,
            title = diaryAlternatives,
            list = diaryAlternativesDescription
        ),
        GuideItem(
            icon = R.drawable.hydration_icon,
            title = hydration,
            list = hydrationDescription
        ),
        GuideItem(
            icon = R.drawable.ginger_icon,
            title = ginger,
            list = gingerDescription
        ),
        GuideItem(
            icon = R.drawable.vegetarian_options,
            title = vegetarianOption,
            list = vegetarianOptionDescription
        ),
        GuideItem(
            icon = R.drawable.nutrient_dense_food_icon,
            title = nutrientDenseFood,
            list = nutrientDenseFoodDescription
        ),
        GuideItem(
            icon = R.drawable.processed_food_icon,
            title = minimizeProcessedFood,
            list = minimizeProcessedFoodDescription
        ),
        GuideItem(
            icon = R.drawable.herbal_tea_icon,
            title = herbalTea,
            list = herbalTeaDescription
        ),
        GuideItem(
            icon = R.drawable.nutrition_plan_icon,
            title = nutritionPlan,
            list = nutritionPlanDescription
        ),
        GuideItem(
            icon = R.drawable.food_safety_icon,
            title = foodSafety,
            list = foodSafetyDescription
        ),
        GuideItem(
            icon = R.drawable.frequent_meal_icon,
            title = frequentMeals,
            list = frequentMealsDescription
        ),
    )
}