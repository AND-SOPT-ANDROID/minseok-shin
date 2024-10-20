//package org.sopt.and.presentation.signup.navigation
//
//
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavOptions
//import androidx.navigation.compose.composable
//
//
//fun NavController.navigationMyPage(navOptions: NavOptions) {
//    navigate(
//        route = MainNavigationBarRoute.MyPage::class.simpleName.orEmpty(),
//        navOptions = navOptions
//    )
//}
//
//fun NavGraphBuilder.myPageNavGraph(
//    padding: PaddingValues,
//    navigateToPointHistory: () -> Unit,
//    navigateToMyCourse: (MyCourseType) -> Unit,
//    navigateToPointGuide: () -> Unit,
//    navigateToSignIn: () -> Unit,
//    navigateToProfile: (ProfileType) -> Unit
//) {
//    composable(route = MainNavigationBarRoute.MyPage::class.simpleName.orEmpty()) {
//        MyPageRoute(
//            padding = padding,
//            navigateToPointHistory = navigateToPointHistory,
//            navigateToMyCourse = navigateToMyCourse,
//            navigateToPointGuide = navigateToPointGuide,
//            navigateToSignIn = navigateToSignIn,
//            navigateToProfile = navigateToProfile
//        )
//    }
//}
