import Main from '@/components/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  title: { String|Number|Function }
 *         显示在侧边栏、面包屑和标签栏的文字
 *         使用'{{ 多语言字段 }}'形式结合多语言使用，例子看多语言的路由配置;
 *         可以传入一个回调函数，参数是当前路由对象，例子看动态路由和带参路由
 *  hideInBread: (false) 设为true后此级路由将不会出现在面包屑中，示例看QQ群路由配置
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面在切换标签后不会缓存，如果需要缓存，无需设置这个字段，而且需要设置页面组件name属性和路由配置的name一致
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true,
          icon: 'md-home'
        },
        component: () => import('@/view/single-page/home')
      }
    ]
  },
  {
    path: '/components',
    name: 'components',
    meta: {
      icon: 'logo-buffer',
      title: '管理',
      access: ['super_admin']
    },
    component: Main,
    children: [
      {
        path: 'user_management',
        name: 'user_management',
        meta: {
          icon: 'md-person',
          title: '用户管理',
          access: ['super_admin']
        },
        component: () => import('@/view/admin/user')
      },
      {
        path: 'good_management',
        name: 'good_management',
        meta: {
          icon: 'md-menu',
          title: '商品管理',
          access: ['super_admin']
        },
        component: () => import('@/view/admin/good')
      },
      {
        path: 'category_management',
        name: 'category_management',
        meta: {
          icon: 'md-menu',
          title: '类别管理',
          access: ['super_admin']
        },
        component: () => import('@/view/admin/category')
      },
      {
        path: 'news_management',
        name: 'news_management',
        meta: {
          icon: 'md-paper-plane',
          title: '新闻管理',
          access: ['super_admin']
        },
        component: () => import('@/view/admin/news')
      },
      {
        path: 'recharge',
        name: 'recharge',
        meta: {
          icon: 'logo-usd',
          title: '充值中心',
          access: ['super_admin']
        },
        component: () => import('@/view/admin/recharge')
      },
      {
        path: 'order_management',
        name: 'order_management',
        meta: {
          icon: 'md-trending-up',
          title: '订单管理',
          access: ['super_admin']
        },
        component: () => import('@/view/admin/order')
      }
    ]
  },
  {
    path: '/Pos',
    name: 'pos',
    meta: {
      icon: 'logo-buffer',
      title: '组件',
      access: ['admin', 'courier']
    },
    component: Main,
    children: [
      {
        path: 'Pos',
        name: 'Pos',
        meta: {
          icon: 'md-grid',
          title: '菜单',
          access: ['admin', 'courier']
        },
        component: () => import('@/view/components/menu/Pos.vue')
      },
      {
        path: 'order',
        name: 'order',
        meta: {
          icon: 'md-menu',
          title: '订单',
          access: ['admin', 'courier']
        },
        component: () => import('@/view/components/menu/order.vue')
      },
      {
        path: 'courier',
        name: 'courier',
        meta: {
          icon: 'md-menu',
          title: '派件',
          access: ['courier']
        },
        component: () => import('@/view/components/menu/courier.vue')
      }

    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/404.vue')
  }
]
