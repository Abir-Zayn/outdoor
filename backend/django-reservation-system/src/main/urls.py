from django.contrib import admin
from django.urls import include, path
from drf_spectacular.views import SpectacularAPIView, SpectacularSwaggerView

urlpatterns = [
    path("admin/", admin.site.urls),
    path("api/account/", include("account.api.urls", "account_api")),
    path(
        "api/password_reset/",
        include("django_rest_passwordreset.urls", namespace="password_reset"),
    ),
    path("api/customer/", include("customer.api.urls", "customer_api")),
    path("api/room/", include("room.api.urls", "room_api")),
    path("api/booking/", include("booking.api.urls", "booking_api")),
    path("api/payment/", include("payment.api.urls", "payment_api")),
    path("api/schema/", SpectacularAPIView.as_view(), name="schema"),
    path(
        "swagger/", SpectacularSwaggerView.as_view(url_name="schema"), name="swagger-ui"
    ),
    path("", include("mcp_server.urls")),
]
