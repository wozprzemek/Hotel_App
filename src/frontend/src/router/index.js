import { createRouter, createWebHistory } from "vue-router";
import Profile from "../views/Profile.vue"
import Client from "../views/Client.vue"
import AdminLogin from "../views/AdminLogin.vue"
import Reservation from "../views/Reservation.vue"
import ReservationsPanel from "../views/ReservationsPanel.vue"
import Room from "../views/Room.vue"
import RoomsPanel from "../views/RoomsPanel.vue"
import GuestsPanel from "../views/GuestsPanel.vue"
import Order from "../views/Order.vue"
import OrderPanel from "../views/OrderPanel.vue"
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
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/admin/reservations/:id',
    name: "Reservation",
    component: Reservation,
  },
  {
    path: '/admin/rooms',
    name: "RoomsPanel",
    component: RoomsPanel,
  },
  {
    path: '/admin/rooms/:id',
    name: "Room",
    component: Room,
  },
  {
    path: '/admin/guests',
    name: "GuestsPanel",
    component: GuestsPanel,
  },
  {
    path: '/admin/orders',
    name: "OrderPanel",
    component: OrderPanel,
  },
  {
    path: '/admin/orders/:id',
    name: "Order",
    component: Order,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
