<template>
    <div></div>
</template>

<script>
import RecipeService from '../services/RecipeService';

export default {
    data() {
        return {
            meal_plan: []
        }
    },
    methods: {
        getAllUserPlans() {
            RecipeService.getNextWeeksPlan().then(response => {
                this.meal_plan = response.data
                console.log(this.userDailyPlans)
            })
        },
    },
    mounted(){
        this.getAllUserPlans()
    }
}

</script>

<style></style>