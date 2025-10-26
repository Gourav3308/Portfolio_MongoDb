#!/bin/bash

# Portfolio Deployment Preparation Script
# Vercel + Render + MongoDB Atlas Setup

echo "🚀 Portfolio Deployment Preparation Script"
echo "=========================================="
echo "Deployment Strategy: Vercel + Render + MongoDB Atlas"
echo ""

# Check if required files exist
echo "📋 Checking required files..."

files=(
    "vercel.json"
    "render.yaml"
    "backend/src/main/resources/application-prod.yml"
    "frontend/src/environments/environment.prod.ts"
    "frontend/src/environments/environment.ts"
)

for file in "${files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file exists"
    else
        echo "❌ $file missing"
    fi
done

echo ""
echo "🔧 Next Steps:"
echo "1. Set up MongoDB Atlas (free tier)"
echo "2. Create Vercel account"
echo "3. Create Render account"
echo "4. Push code to GitHub"
echo "5. Deploy backend service on Render"
echo "6. Deploy frontend service on Vercel"
echo "7. Configure environment variables"
echo ""
echo "📖 See DEPLOYMENT_GUIDE.md for detailed instructions"
echo "📋 See DEPLOYMENT_CHECKLIST.md for step-by-step checklist"
echo "🔧 See ENVIRONMENT_SETUP.md for environment variables guide"
