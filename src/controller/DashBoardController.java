package controller;

import dao.DashBoardDAO;
import model.FlightModel;
import model.UserSession;
import view.DashBoard;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import view.Login;

public class DashBoardController {
    private final DashBoard dashboardView;
    private final DashBoardDAO dashboardDAO;
    private final UserSession userSession;
    private List<FlightModel> currentFlights;

    public DashBoardController(DashBoard dashboardView) {
        this.dashboardView = dashboardView;
        this.dashboardDAO = new DashBoardDAO();
        this.userSession = UserSession.getInstance();
        this.currentFlights = new ArrayList<>();
        
        // Initialize
        initializeView();
        loadDashboardData();
        attachEventHandlers();
    }

    private void initializeView() {
        try {
            // Show user info
            if (userSession.isLoggedIn()) {
                dashboardView.setUserInfo("Welcome, " + userSession.getUserEmail());
            } else {
                dashboardView.setUserInfo("Welcome, Guest");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error initializing view: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDashboardData() {
        try {
            // Load initial flights
            currentFlights = dashboardDAO.getAllFlights();
            if (currentFlights != null && !currentFlights.isEmpty()) {
                dashboardView.displayFlights(currentFlights);
            }
            
            // Show dashboard panel by default
            dashboardView.showDashboardPanel();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboardView, 
                "Error loading data: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void attachEventHandlers() {
        try {
            // Dashboard menu click
            dashboardView.addDashboardMenuListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showDashboard();
                }
            });

            // Flights menu click
            dashboardView.addFlightsMenuListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showFlights();
                }
            });

            // Logout menu click
            dashboardView.addLogoutMenuListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    logout();
                }
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboardView, 
                "Error setting up event handlers: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDashboard() {
        try {
            dashboardView.showDashboardPanel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboardView, 
                "Error loading dashboard: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showFlights() {
        try {
            currentFlights = dashboardDAO.getAllFlights();
            dashboardView.displayFlights(currentFlights);
            dashboardView.showFlightsPanel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboardView, 
                "Error loading flights: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        userSession.logout();
        dashboardView.dispose();
        
        // Show login screen
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            new LoginController(login);
            login.setVisible(true);
        });
        
        JOptionPane.showMessageDialog(null, 
            "Logged out successfully", 
            "Logout", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}