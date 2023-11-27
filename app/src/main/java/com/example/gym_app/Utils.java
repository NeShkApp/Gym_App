package com.example.gym_app;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<Training> trainings;
    private static ArrayList<Plan> plans;

    public static void initTrainings(){
        if(null == trainings){
            trainings = new ArrayList<>();

        }

        Training pushUps = new Training(1, "Push-Ups",
                "Push-ups are a classic bodyweight exercise that involve lowering and raising your body using your arms.",
                "Push-ups are a highly effective exercise that strengthen the chest, shoulders, and triceps. They also engage the core and can be modified for various fitness levels.",
                "https://cdn2.stylecraze.com/wp-content/uploads/2021/08/Regular-Push-Ups.jpg");
        trainings.add(pushUps);

        Training squats = new Training(2, "Squats",
                "Squats are a lower-body exercise that require bending your knees and hips to lower your body toward the ground.",
                "Squats target the quadriceps, hamstrings, and glutes. They are excellent for building leg strength and can be performed with or without weights.",
                "https://www.chicagospringhalf.com/wp-content/uploads/sites/32/2017/06/Screen-Shot-2017-06-22-at-3.25.47-PM-1024x640.png");
        trainings.add(squats);

        Training planks = new Training(3, "Planks",
                "Planks are a core-strengthening exercise where you hold a push-up position with your body in a straight line.",
                "Planks are great for strengthening the abdominal muscles, back, and shoulders. They also improve overall core stability and posture.",
                "https://marvel-b1-cdn.bc0a.com/f00000000229348/www.silversneakers.com/wp-content/uploads/2019/08/SSBlog_PlankVariation_700x525.jpg");
        trainings.add(planks);

        Training lunges = new Training(4, "Lunges",
                "Lunges involve taking a step forward or backward and bending your front knee while keeping your back leg straight.",
                "Lunges work the quadriceps, hamstrings, and glutes. They also enhance balance and flexibility and can be done with or without weights.",
                "https://julielohre.com/wp-content/uploads/2018/07/AlternatingLunges-1.jpg");
        trainings.add(lunges);

        Training jumpingJacks = new Training(5, "Jumping Jacks",
                "Jumping jacks are a cardiovascular exercise that requires jumping.",
                "Jumping jacks elevate your heart rate, making them a great warm-up or cardio exercise. They also improve coordination and agility.",
                "https://fiverr-res.cloudinary.com/videos/t_main1,q_auto,f_auto/hl8dc4mi0yb0xyq29rr6/to-write-you-how-to-do-the-jumping-jack-exercise-correctly.png");
        trainings.add(jumpingJacks);

        Training burpees = new Training(6, "Burpees",
                "Burpees are a high-intensity full-body exercise that combines a squat, push-up, and explosive jump.",
                "Burpees provide a full-body workout, targeting muscles in the chest, arms, legs, and core. They are excellent for cardiovascular fitness and strength training.",
                "https://media.istockphoto.com/id/1147316374/pl/wektor/przewodnik-%C4%87wicze%C5%84-z-kobiet%C4%85-robi-squat-thrust-burpee-pozycji-w-3-kroku.jpg?s=170667a&w=0&k=20&c=OHXh3qCFXp71GcYvRe96HQFD-QOSGlqpJa7PD4IkSIg=");
        trainings.add(burpees);

    }

    public static ArrayList<Training> getTrainings() {
        return trainings;
    }


    public static boolean addPlan(Plan plan){
        if(null == plans){
            plans = new ArrayList<>();
        }

        return plans.add(plan);
    }

    public static ArrayList<Plan> getPlans() {
        return plans;
    }

    public static boolean removePlan(Plan plan){
        return plans.remove(plan);
    }
}
