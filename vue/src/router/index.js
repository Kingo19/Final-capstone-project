import {createRouter as createRouter, createWebHistory} from 'vue-router'
import {useStore} from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import RecipeListView from '../views/RecipeListView.vue';
import RecipeDetailView from '../views/RecipeDetailView.vue';
import AddRecipe from "@/components/AddRecipe.vue";
import modifyRecipe from "@/components/ModifyRecipe.vue";
import addMealPlan from "@/Potential/addMealPlan.vue";
import addMeal from "@/components/addMeal.vue";
import test1 from "@/components/test1.vue";
import test2 from "@/components/test2.vue";
import test3 from "@/components/test3.vue";
import test5 from "@/components/test5.vue";
import test4 from "@/components/test4.vue";
import test6 from "@/components/test6.vue";
import test7 from "@/components/test7.vue";
import test8 from "@/components/test8.vue";
import test9 from "@/components/test9.vue";
import test10 from "@/components/test10.vue";
import testAddRecipe from "@/components/testAddRecipe.vue";

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/login",
        name: "login",
        component: LoginView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/logout",
        name: "logout",
        component: LogoutView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/register",
        name: "register",
        component: RegisterView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/all/recipes",
        name: "recipeList",
        component: RecipeListView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/all/recipe",
        name: "recipeDetailView",
        component: RecipeDetailView,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/recipes/add',
        name: "addRecipe",
        component: testAddRecipe,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test1',
        name: "test1",
        component: test1,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test2',
        name: "test2",
        component: test2,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test3',
        name: "test3",
        component: test3,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test4',
        name: "cardrecipe",
        component: test4,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test5',
        name: "test5",
        component: test5,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test6',
        name: "test6",
        component: test6,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test7',
        name: "test7",
        component: test7,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test8',
        name: "test8",
        component: test8,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test9',
        name: "test9",
        component: test9,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/test10',
        name: "test10",
        component: test10,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/addMeal',
        name: "addMeal",
        component: addMeal,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/testaddrecipe',
        name: "testaddrecipe",
        component: testAddRecipe,
        meta: {
            requiresAuth: false
        }
    },



];

// Create the router
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

router.beforeEach((to) => {

    // Get the Vuex store
    const store = useStore();

    // Determine if the route requires Authentication
    const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

    // If it does and they are not logged in, send the user to "/login"
    if (requiresAuth && store.state.token === '') {
        return {name: "login"};
    }
    // Otherwise, do nothing and they'll go to their next destination
});

export default router;
