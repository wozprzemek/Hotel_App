import { createRouter, createWebHistory } from "vue-router";
import Profile from "../views/Profile.vue"
import Client from "../views/Client.vue"
import AdminLogin from "../views/AdminLogin.vue"
import Reservation from "../views/Reservation.vue"
import ReservationsPanel from "../views/ReservationsPanel.vue"
const routes = [
  {
    path: "/",
    name: "Client",
    component: Client,
  },
  {
    path: "/admin",
    name: "AdminLogin",
    component: AdminLogin,
  },
  {
    path: "/profile/:id",
    name: "Profile",
    component: Profile,
  },
  {
    path: '/admin/reservations',
    name: "ReservationsPanel",
    component: ReservationsPanel,
  },
  {
    path: '/admin/reservations/:id',
    name: "Reservation",
    component: Reservation,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
